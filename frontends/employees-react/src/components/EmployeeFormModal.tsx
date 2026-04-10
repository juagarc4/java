import type { Employee } from '../models/Employee'

interface EmployeeFormModalProps {
  show: boolean
  employee: Employee
  onClose: () => void
  onSubmit: (e: React.SubmitEvent<HTMLFormElement>) => void
  onChange: (updated: Employee) => void
}

export const EmployeeFormModal: React.FC<EmployeeFormModalProps> = ({
  show,
  employee,
  onClose,
  onSubmit,
  onChange,
}) => {
  if (!show) return null

  return (
    <div
      className='modal show d-block'
      tabIndex={-1}
      style={{ backgroundColor: 'rgba(15, 23, 42, 0.6)', backdropFilter: 'blur(8px)' }}
    >
      <div className='modal-dialog modal-dialog-centered'>
        <div className='modal-content border-0 shadow-lg rounded-4'>
          <div className='modal-header border-bottom-0 pb-0 pt-4 px-4'>
            <h5 className='modal-title fw-bold fs-4'>
              {employee.id > 0 ? 'Actualizar Registro de Mérito' : 'Nueva Entrada en el Registro'}
            </h5>
            <button type='button' className='btn-close' onClick={onClose}></button>
          </div>
          <form onSubmit={onSubmit}>
            <div className='modal-body p-4'>
              <div className='mb-4'>
                <label className='form-label small text-uppercase fw-bold text-muted mb-1'>First Name</label>
                <input
                  type='text'
                  className='form-control form-control-lg bg-light border-0 fs-6 shadow-none'
                  value={employee.firstName}
                  onChange={(e) => onChange({ ...employee, firstName: e.target.value })}
                  required
                />
              </div>
              <div className='mb-4'>
                <label className='form-label small text-uppercase fw-bold text-muted mb-1'>Last Name</label>
                <input
                  type='text'
                  className='form-control form-control-lg bg-light border-0 fs-6 shadow-none'
                  value={employee.lastName}
                  onChange={(e) => onChange({ ...employee, lastName: e.target.value })}
                  required
                />
              </div>
              <div className='mb-4'>
                <label className='form-label small text-uppercase fw-bold text-muted mb-1'>Department</label>
                <input
                  type='text'
                  className='form-control form-control-lg bg-light border-0 fs-6 shadow-none'
                  value={employee.department}
                  onChange={(e) => onChange({ ...employee, department: e.target.value })}
                  required
                />
              </div>
              <div className='mb-4'>
                <label className='form-label small text-uppercase fw-bold text-muted mb-1'>Position</label>
                <input
                  type='text'
                  className='form-control form-control-lg bg-light border-0 fs-6 shadow-none'
                  value={employee.position}
                  onChange={(e) => onChange({ ...employee, position: e.target.value })}
                  required
                />
              </div>
              <div className='mb-2'>
                <label className='form-label small text-uppercase fw-bold text-muted mb-1'>Salary</label>
                <div className='input-group input-group-lg shadow-none'>
                  <span className='input-group-text bg-light border-0 text-muted'>CHF</span>
                  <input
                    type='number'
                    className='form-control bg-light border-0 fs-6 ps-0 shadow-none'
                    value={employee.salary || ''}
                    onChange={(e) => onChange({ ...employee, salary: Number(e.target.value) })}
                    required
                  />
                </div>
              </div>
            </div>
            <div className='modal-footer border-top-0 pt-0 pb-4 px-4'>
              <button type='button' className='btn btn-light px-3 fw-bold' onClick={onClose}>
                Cancel
              </button>
              <button type='submit' className='btn btn-primary px-4 fw-bold shadow-sm'>
                Save
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
