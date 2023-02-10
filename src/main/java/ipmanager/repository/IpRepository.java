package ipmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ipmanager.model.Ip;

public interface IpRepository extends CrudRepository<Ip, Long> {
	@Override
	public List<Ip> findAll();

}
