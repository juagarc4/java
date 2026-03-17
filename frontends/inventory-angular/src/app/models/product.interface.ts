export interface Product {
  id?: number
  sku: string
  name: string
  description: string
  minStock: number
  unitCost: number
  stock: number
  updatedAt?: string
}
