package it.unicam.hackhub.hackhub.Application.Abstraction.Repository;

import it.unicam.hackhub.hackhub.Core.models.MembroStaff;

import java.util.Optional;

public interface IRepositoryMembriStaff {
    Optional<MembroStaff> findMembroStaffById(Long id);
    Optional<MembroStaff> updateMembroStaff(MembroStaff membroStaff);
}
