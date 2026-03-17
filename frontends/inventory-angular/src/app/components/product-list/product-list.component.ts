import { Component, inject, OnInit } from '@angular/core'
import { DatePipe } from '@angular/common'
import { Product } from '../../models/product.interface'
import { ProductService } from '../../core/services/product.service'
import { RouterLink, RouterLinkActive, RouterLinkWithHref } from '@angular/router'

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [DatePipe, RouterLink, RouterLinkActive],
  templateUrl: './product-list.component.html',
})
export class ProductListComponent implements OnInit {
  products: Product[] = []

  private productService = inject(ProductService)
  ngOnInit(): void {
    this.getAllProducts()
  }
  onDelete(id: number): void {
    if (confirm(`Are you sure you want to delete product with ID ${id}?`)) {
      this.productService.deleteProduct(id).subscribe({
        next: (serverMessage: string) => {
          console.log('Product successfully deleted')
          alert(serverMessage)
          this.getAllProducts()
        },
        error: (err) => {
          console.error('Delete operation failed:', err)
          const message = err.error?.message || 'Unexpected error during deletion'
          alert(`Could not delete product: ${message}`)
        },
      })
    }
  }

  private getAllProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.products = data
      },
      error: (error) => {
        console.log('Error retrieving products', error)
      },
    })
  }
}
