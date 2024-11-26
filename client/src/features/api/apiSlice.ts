import { RootState } from '@/store'
import {
  BaseQueryFn,
  createApi,
  FetchArgs,
  fetchBaseQuery,
  FetchBaseQueryError,
} from '@reduxjs/toolkit/query/react'
import { toast } from 'sonner'
import Cookies from 'universal-cookie'
import { userLoggedIn, userLoggedOut } from '../auth/authSlice'

const cookies = new Cookies(null, { path: '/' })

const baseQuery = fetchBaseQuery({
  baseUrl: import.meta.env.VITE_SERVER_URL,
  prepareHeaders: (headers, { getState }) => {
    // By default, if we have a token in the store, let's use that for authenticated requests
    const token = (getState() as RootState).authReducer.token || cookies.get('accessToken')
    if (token) {
      headers.set('authorization', `Bearer ${token}`)
    }
    return headers
  },
})
const baseQueryWithReauth: BaseQueryFn<string | FetchArgs, unknown, FetchBaseQueryError> = async (
  args,
  api,
  extraOptions,
) => {
  let result = await baseQuery(args, api, extraOptions)
  if (result.error && result.error.status === 401) {
    // try to get a new token
    const refreshToken =
      (api.getState() as RootState).authReducer.refreshToken || cookies.get('refreshToken')
    const refreshResult = await baseQuery(
      {
        url: 'auth/refresh',
        method: 'POST',
        body: { token: refreshToken },
      },
      api,
      extraOptions,
    )
    if (refreshResult.data) {
      const tokens = refreshResult.data as {
        token: string
        refreshToken: string
      }
      // store the new token
      api.dispatch(
        userLoggedIn({
          token: tokens.token,
          refreshToken: tokens.refreshToken,
        }),
      )
      cookies.set('accessToken', tokens.token, { path: '/' })
      cookies.set('refreshToken', tokens.refreshToken, { path: '/' })
      // retry the initial query
      result = await baseQuery(args, api, extraOptions)
    } else {
      if ((result.error.data as { message: string }).message === 'Unauthorized') {
        toast.error('Your session has expired. Please log in again.', {
          action: {
            label: 'Log in',
            onClick: () => {
              cookies.remove('accessToken')
              cookies.remove('refreshToken')
              api.dispatch(userLoggedOut())
              window.location.href = '/login'
            },
          },
        })
      }
    }
  }
  return result
}

export const apiSlice = createApi({
  reducerPath: 'api',
  baseQuery: baseQueryWithReauth,
  tagTypes: [
    'Auth',
    'Service',
    'Bills',
    'Reports',
    'Apartments',
    'Surveys',
    'RejectionReasons',
    'Users',
    'Settings',
    'Packages',
  ],
  endpoints: () => ({}),
})