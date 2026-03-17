import { Routes } from '@angular/router'
import { ProductListComponent } from './components/product-list/product-list.component'
import { ProductFormComponent } from './components/product-form/product-form.component'

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  {
    path: 'products',
    children: [
      { path: '', component: ProductListComponent },
      { path: 'new', component: ProductFormComponent },
      { path: 'edit/:id', component: ProductFormComponent },
    ],
  },
]
