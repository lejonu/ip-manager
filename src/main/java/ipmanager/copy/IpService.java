package ipmanager.copy;

import java.util.List;

import ipmanager.model.Ip;

/**
 * 
 * @author leonardo
 *
 */
public interface IpService {
	
	List<Ip> findAll();
	
	Ip findById(Long id);
	
	void save(Ip ip);
	
	void update(Long id, Ip ip);
	
	void delete(Long id);
	
}
