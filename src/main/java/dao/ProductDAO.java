package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ProductDAO")
public interface ProductDAO extends JpaRepository<Product, Long>{

}
