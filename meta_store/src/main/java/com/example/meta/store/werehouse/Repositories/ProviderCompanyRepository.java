package com.example.meta.store.werehouse.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.example.meta.store.Base.Repository.BaseRepository;
import com.example.meta.store.werehouse.Entities.ProviderCompany;

public interface ProviderCompanyRepository extends BaseRepository<ProviderCompany, Long> {

	/////////////////////////////////////////////////////// real work ///////////////////////////////////////////////////
	
	//@Query("SELECT p FROM ProviderCompany p WHERE p.company.id = :companyId AND p.isDeleted = false")
	List<ProviderCompany> findAllByCompanyIdAndIsDeletedFalse(Long companyId);

	boolean existsByProviderIdAndCompanyId(Long id, Long id2);
	
	Optional<ProviderCompany> findByProviderIdAndCompanyIdAndIsDeletedFalse(Long providerId, Long companyId);
	
	void deleteByProviderIdAndCompanyId(Long id, Long id2);
	
	@Query("SELECT p FROM ProviderCompany p WHERE "
			+ "(p.company.id = :companyId)"
			+ "AND (p.provider.name LIKE %:search% OR p.provider.code LIKE %:search%) "
			)
	List<ProviderCompany> findAllByNameContainingOrCodeContainingAndCompanyId(String search, Long companyId);

	
	/////////////////////////////////////////////////////// not work ///////////////////////////////////////////////////

	void deleteByCompanyIdAndProviderId(Long id, Long id2);
	void deleteByProviderId(Long id);

	Optional<ProviderCompany> findByProviderIdAndCompanyIdAndIsDeletedTrue(Long providerId, Long companyId);
}
