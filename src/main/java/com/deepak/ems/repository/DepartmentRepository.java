    package com.deepak.ems.repository;

    import com.deepak.ems.entity.Department;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface DepartmentRepository extends JpaRepository<Department, Long> {
    }
