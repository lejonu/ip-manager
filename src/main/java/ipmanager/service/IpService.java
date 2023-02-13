package ipmanager.service;

import java.util.List;

import ipmanager.model.Ip;

/**
 * Defines Strategy pattern in Ip Domain
 * So we may have multiple implementations of this interface
 * 
 * @author Leonardo José Nunes
 */
public interface IpService {

	List<Ip> findAll();

	Ip findById(Long id);

	void save(Ip ip);

	void update(Long id, Ip ip);

	void delete(Long id);

}
