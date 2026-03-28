package it.unicam.hackhub.hackhub.Infrastructure.Adapter;

import it.unicam.hackhub.hackhub.Application.Abstraction.Repository.IRepositoryMembriStaff;
import it.unicam.hackhub.hackhub.Core.models.MembroStaff;
import it.unicam.hackhub.hackhub.Infrastructure.Repository.RepositoryMembriStaffJpa;

import java.util.Optional;

public class RepositoryMembriStaffAdp implements IRepositoryMembriStaff {
    private final RepositoryMembriStaffJpa repositoryMembriStaffJpa;

    public RepositoryMembriStaffAdp(RepositoryMembriStaffJpa repositoryMembriStaffJpa) {
        this.repositoryMembriStaffJpa = repositoryMembriStaffJpa;
    }

    @Override
    public Optional<MembroStaff> findMembroStaffById(Long id) {
        return repositoryMembriStaffJpa.findById(id);
    }

    @Override
    public Optional<MembroStaff> updateMembroStaff(MembroStaff membroStaff) {
        return Optional.of(repositoryMembriStaffJpa.save(membroStaff));
    }
}
