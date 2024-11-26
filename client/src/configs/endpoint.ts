export const ROUTES = {
	LOGIN: '/login',
	PAYMENT: '/payment',
  HOME: '/',
  PACKAGES: '/packages',
  SURVEYS: '/surveys',
  REPORTS: '/reports',
  BILLS: '/bills',
  CHAT: '/chat',
  ADMIN: {
		HOME: '/admin',
		APARTMENTS: '/admin/apartments',
    SERVICES: '/admin/services',
    USERS: '/admin/users',
    PACKAGES: '/admin/packages',
    BILLS: '/admin/bills',
    SURVEYS: '/admin/surveys',
    REPORTS: '/admin/reports',
    SETTINGS: '/admin/settings',
		CHAT: '/admin/chat',
  },
} as const
