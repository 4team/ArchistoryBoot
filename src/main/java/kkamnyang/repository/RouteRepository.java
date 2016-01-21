package kkamnyang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

import kkamnyang.domain.RouteVO;

@RepositoryDefinition(domainClass=RouteVO.class,idClass=Long.class)
public interface RouteRepository extends CrudRepository<RouteVO, Integer> {

	@Override
	default long count() {
		return 0;
	}

	@Override
	default void delete(Integer arg0) {
		
	}

	@Override
	default boolean exists(Integer arg0) {
		return false;
	}

	@Override
	default RouteVO findOne(Integer arg0) {
		return null;
	}

	@Override
	default <S extends RouteVO> S save(S arg0) {
		return null;
	}

}
