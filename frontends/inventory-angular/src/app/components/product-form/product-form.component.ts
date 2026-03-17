import { Component, OnInit, inject } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'
import { Router, RouterModule, ActivatedRoute } from '@angular/router'

import { ProductService } from '../../core/services/product.service'
import { Product } from '../../models/product.interface'
import { Observable } from 'rxjs'

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [RouterModule, CommonModule, ReactiveFormsModule],
  templateUrl: './product-form.component.html',
})
export class ProductFormComponent implements OnInit {
  private fb = inject(FormBuilder)
  private productService = inject(ProductService)
  private router = inject(Router)
  private readonly route = inject(ActivatedRoute)
  private currentProductId: number | null = null

  productForm!: FormGroup
  errorMessage: string = ''
  errorDetails: string[] = []
  isEditMode = false

  ngOnInit(): void {
    this.initForm()
    const id = this.route.snapshot.params['id']
    if (id) {
      this.isEditMode = true
      this.loadProduct(+id)
    }
  }

  private initForm(): void {
    this.productForm = this.fb.group({
      id: [null],
      sku: ['', [Validators.required, Validators.minLength(3)]],
      name: ['', [Validators.required]],
      description: [''],
      stock: [0, [Validators.required, Validators.min(0)]],
      minStock: [0, [Validators.required, Validators.min(0)]],
      unitCost: [0, [Validators.required, Validators.min(0.01)]],
      version: [null],
    })
  }

  saveProduct(): void {
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched()
      return
    }

    const productData: Product = this.productForm.value
    const request = this.isEditMode
      ? this.productService.updateProduct(this.currentProductId!, productData)
      : this.productService.createProduct(productData)

    request.subscribe({
      next: () => this.router.navigate(['/products']),
      error: (err) => {
        console.log(err)
        this.errorMessage = err.error.message || 'Unexpected error'
        this.errorDetails = err.error.details || []
        window.scrollTo({ top: 0, behavior: 'smooth' })
      },
    })
  }

  checkStockWarning(): boolean {
    const stock = this.productForm.get('stock')?.value
    const minStock = this.productForm.get('minStock')?.value

    return stock !== null && minStock !== null && stock < minStock
  }

  loadProduct(id: number): void {
    this.productService.getProductById(id).subscribe({
      next: (product) => {
        this.currentProductId = id
        this.productForm.patchValue(product)
      },
      error: (err) => {
        this.errorMessage = 'Could not load product data'
      },
    })
  }
}
