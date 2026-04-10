import React, { useState } from 'react'
import { createEmployee } from '../service/EmployeeService'
import type { Employee } from '../models/Employee'

interface Props {
  onSuccess: () => void
}

export const AddEmployee: React.FC<Props> = ({ onSuccess }) => {
  const [employee, setEmployee] = useState<Omit<Employee, 'id'>>({
    firstName: '',
    lastName: '',
    department: '',
    position: '',
    salary: 0,
  })

  const handleSubmit = async (e: React.SubmitEvent<HTMLFormElement>) => {
    e.preventDefault()
    try {
      await createEmployee(employee)
      alert('Success: Employee record created.')
      setEmployee({
        firstName: '',
        lastName: '',
        department: '',
        position: '',
        salary: 0,
      })
      onSuccess()
    } catch (error) {
      console.error('Workflow Disruption:', error)
    }
  }

  return (
    <form onSubmit={handleSubmit} className='card p-4 mb-4 shadow-sm'>
      <h3 className='h5 text-secondary'>Add new employee</h3>
      <div className='mb-3'>
        <input
          className='form-control'
          placeholder='First Name'
          value={employee.firstName}
          onChange={(e) => setEmployee({ ...employee, firstName: e.target.value })}
          required
        />
      </div>
      <div className='mb-3'>
        <input
          className='form-control'
          placeholder='Last Name'
          value={employee.lastName}
          onChange={(e) => setEmployee({ ...employee, lastName: e.target.value })}
          required
        />
      </div>
      <div className='mb-3'>
        <input
          className='form-control'
          placeholder='Department'
          value={employee.department}
          onChange={(e) => setEmployee({ ...employee, department: e.target.value })}
          required
        />
      </div>
      <div className='mb-3'>
        <input
          className='form-control'
          placeholder='Position'
          value={employee.position}
          onChange={(e) => setEmployee({ ...employee, position: e.target.value })}
          required
        />
      </div>
      <div className='mb-3'>
        <input
          type='number'
          className='form-control'
          placeholder='Salary'
          value={employee.salary || ''}
          onChange={(e) => setEmployee({ ...employee, salary: Number(e.target.value) })}
          required
        />
      </div>
      <button type='submit' className='btn btn-primary w-100'>
        Save
      </button>
    </form>
  )
}
