import { forwardRef, useCallback, useEffect, useImperativeHandle, useState } from 'react'
import type { Employee } from '../models/Employee'
import { deleteEmployee, getAllEmployees } from '../service/EmployeeService'

interface EmployeeListProps {
  onEdit: (employee: Employee) => void
  onDataSummary: (count: number, totalPayroll: number) => void
}

export interface EmployeeListHandle {
  refresh: () => Promise<void>
}

export const EmployeeList = forwardRef<EmployeeListHandle, EmployeeListProps>(({ onEdit, onDataSummary }, ref) => {
  const [employees, setEmployees] = useState<Employee[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  // Memorizamos fetchRecords para evitar recreaciones innecesarias y satisfacer las dependencias de useEffect
  const fetchRecords = useCallback(async () => {
    setLoading(true)
    setError(null)
    try {
      const data = await getAllEmployees()
      setEmployees(data)
      const total = data.reduce((acc, curr) => acc + curr.salary, 0)
      onDataSummary(data.length, total)
    } catch (err: unknown) {
      console.error('List Sync Error:', err)
      if (err instanceof Error) {
        setError(err.message)
      } else {
        setError('An unexpected error occurred during synchronization.')
      }
    } finally {
      setLoading(false)
    }
  }, [onDataSummary])

  useImperativeHandle(ref, () => ({
    refresh: fetchRecords,
  }))

  useEffect(() => {
    fetchRecords()
  }, [fetchRecords])

  const handleDelete = async (id: number) => {
    if (confirm('Are you sure you want to delte this employee? This can not be undone.')) {
      try {
        await deleteEmployee(id)
        fetchRecords()
      } catch (err) {
        console.error('Error deleting employee:', err)
      }
    }
  }

  return (
    <div className='card shadow-sm border-0 rounded-4 overflow-hidden'>
      <div className='table-responsive'>
        <table className='table table-hover align-middle mb-0'>
          <thead className='table-light text-secondary'>
            <tr>
              <th className='ps-4 py-3'>ID</th>
              <th className='py-3'>First Name</th>
              <th className='py-3'>Last Name</th>
              <th className='py-3'>Departament</th>
              <th className='py-3'>Position</th>
              <th className='py-3'>Salary</th>
              <th className='text-end pe-4 py-3'>Actions</th>
            </tr>
          </thead>
          <tbody>
            {loading ? (
              <tr>
                <td colSpan={5} className='text-center py-5'>
                  <div className='spinner-border text-primary spinner-border-sm me-2' role='status'></div>
                  <span className='text-muted'>Syncronizing data...</span>
                </td>
              </tr>
            ) : error ? (
              <tr>
                <td colSpan={5} className='text-center py-5 px-4'>
                  <div className='alert alert-warning border-0 mb-3 shadow-sm d-inline-block'>
                    <i className='bi bi-exclamation-triangle-fill me-2'></i>
                    {error}
                  </div>
                  <br />
                  <button className='btn btn-outline-primary btn-sm' onClick={fetchRecords}>
                    <i className='bi bi-arrow-clockwise me-1'></i> Try connectiong again
                  </button>
                </td>
              </tr>
            ) : employees.length > 0 ? (
              employees.map((emp) => (
                <tr key={emp.id}>
                  <td className='ps-4 text-muted'>#{emp.id}</td>
                  <td>
                    <div className='fw-bold text-dark'>{emp.firstName}</div>
                  </td>
                  <td>
                    <div className='fw-bold text-dark'>{emp.lastName}</div>
                  </td>
                  <td>
                    <span className='badge bg-blue-subtle text-primary border border-primary-subtle px-2'>
                      {emp.department}
                    </span>
                  </td>
                  <td>
                    <span className='badge bg-blue-subtle text-primary border border-primary-subtle px-2'>
                      {emp.position}
                    </span>
                  </td>
                  <td className='font-monospace'>CHF {emp.salary.toLocaleString('de-CH')}</td>
                  <td className='text-end pe-4'>
                    <div className='btn-group shadow-sm rounded-2 border'>
                      <button
                        className='btn btn-white btn-sm border-0 border-end'
                        onClick={() => onEdit(emp)}
                        title='Editar'
                      >
                        <i className='bi bi-pencil-square text-primary'></i>
                      </button>
                      <button
                        className='btn btn-white btn-sm border-0'
                        onClick={() => handleDelete(emp.id)}
                        title='Eliminar'
                      >
                        <i className='bi bi-trash3 text-danger'></i>
                      </button>
                    </div>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={5} className='text-center py-5'>
                  <i className='bi bi-database-exclamation fs-1 text-light-emphasis mb-3 d-block'></i>
                  <p className='text-muted mb-0'>No employee data found.</p>
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  )
})
