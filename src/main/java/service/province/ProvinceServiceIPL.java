package service.province;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import repository.MyProvinceRepository;

public class ProvinceServiceIPL implements MyProvinceService {
    @Autowired
    private MyProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }
}
