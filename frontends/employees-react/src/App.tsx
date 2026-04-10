import { useCallback, useRef, useState } from 'react'
import { EmployeeList, type EmployeeListHandle } from './components/EmployeeList'
import type { Employee } from './models/Employee'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
import { EmployeeFormModal } from './components/EmployeeFormModal'
import { updateEmployee, createEmployee } from './service/EmployeeService'

const EMPTY_EMPLOYEE: Employee = { id: 0, firstName: '', lastName: '', department: '', position: '', salary: 0 }

export default function App() {
  const [currentEmployee, setCurrentEmployee] = useState<Employee>(EMPTY_EMPLOYEE)
  const [showModal, setShowModal] = useState(false)
  const [summary, setSummary] = useState({ count: 0, payroll: 0 })
  const listRef = useRef<EmployeeListHandle>(null)

  const handleAddNew = () => {
    setCurrentEmployee(EMPTY_EMPLOYEE)
    setShowModal(true)
  }

  const handleEdit = (emp: Employee) => {
    setCurrentEmployee(emp)
    setShowModal(true)
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    try {
      if (currentEmployee.id > 0) {
        await updateEmployee(currentEmployee.id, currentEmployee)
      } else {
        const { id, ...newEmployeeData } = currentEmployee
        await createEmployee(newEmployeeData)
      }
      setShowModal(false)
      listRef.current?.refresh()
    } catch (error: unknown) {
      const message = error instanceof Error ? error.message : 'Unknown error'
      console.error('Fallo en la transacción de base de datos:', message)
    }
  }

  return (
    <div className='container py-5'>
      <div className='d-flex justify-content-between align-items-center mb-4'>
        <div className='d-flex align-items-center'>
          <div className='bg-primary text-white rounded-3 p-2 me-3 shadow-sm'>
            <i className='bi bi-shield-check fs-4'></i>
          </div>
          <div>
            <h1 className='h3 fw-bold text-dark mb-0'>HR System</h1>
          </div>
        </div>
        <button className='btn btn-primary d-flex align-items-center gap-2 shadow-sm px-4' onClick={handleAddNew}>
          <i className='bi bi-person-plus'></i> New employee
        </button>
      </div>

      <EmployeeList
        ref={listRef}
        onEdit={handleEdit}
        onDataSummary={useCallback((count, payroll) => setSummary({ count, payroll }), [])}
      />

      <EmployeeFormModal
        show={showModal}
        employee={currentEmployee}
        onClose={() => setShowModal(false)}
        onChange={setCurrentEmployee}
        onSubmit={handleSubmit}
      />

      <div className='mt-3 text-end px-2'>
        <span className='text-muted small'>
          Active registry count: <strong>{summary.count}</strong> | Total salaries:{' '}
          <strong>CHF {summary.payroll.toLocaleString('de-CH')}</strong>
        </span>
      </div>
    </div>
  )
}
