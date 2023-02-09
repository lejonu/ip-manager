package ipmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ipmanager.model.Ip;

@Repository
public interface IpRepository extends CrudRepository<Ip, Long> {
	@Override
	public List<Ip> findAll();
}
