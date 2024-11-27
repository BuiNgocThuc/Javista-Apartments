import { ApartmentUserRoleSchema } from '@/enums'
import { z } from 'zod'
import { UserSchema } from './user.validate'
import { BaseEntitySchema } from './base.entity'
export const RelationshipsSchema = z.object({
  id: z.number(),
  role: ApartmentUserRoleSchema, // adjust enums based on all possible roles
  userId: z.number(),
  apartmentId: z.string(),
})

type RelationshipsType = z.infer<typeof RelationshipsSchema> & {
  user?: z.infer<typeof UserSchema> | null
  createdAt?: Date | null | string
  updatedAt?: Date | null | string
  deleteAt?: Date | null | string
}

export const ExtendedRelationshipsSchema: z.ZodType<RelationshipsType> = RelationshipsSchema.merge(BaseEntitySchema)

export type RelationshipsTypeSchema = z.infer<typeof ExtendedRelationshipsSchema>
