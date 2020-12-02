package repository;

import model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MyProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
