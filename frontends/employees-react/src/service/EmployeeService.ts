import type { Employee } from '../models/Employee'

const API_URL = 'http://localhost:8080/api/employees'

export const getAllEmployees = async (): Promise<Employee[]> => {
  const response = await fetch(API_URL)
  if (!response.ok) throw new Error('System Error: Could not fetch employee list.')
  return await response.json()
}

export const createEmployee = async (employee: Omit<Employee, 'id'>): Promise<Employee> => {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(employee),
  })
  if (!response.ok) throw new Error('System Error: Employee registration failed.')
  return await response.json()
}

export const updateEmployee = async (id: number, employee: Employee): Promise<Employee> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(employee),
  })

  if (!response.ok) {
    throw new Error(`System Error: Could not update employee ID ${id}.`)
  }

  return await response.json()
}

export const deleteEmployee = async (id: number): Promise<string> => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  })
  if (!response.ok) throw new Error(`System Error: Could not remove employee ID ${id}.`)
  return await response.text() // Recibe el mensaje de confirmación del Backend
}
