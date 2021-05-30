package dio.santander.bootcamp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dio.santander.bootcamp.mapper.StockMapper;
import dio.santander.bootcamp.exceptions.BusinessExceptions;
import dio.santander.bootcamp.exceptions.NotFoundException;
import dio.santander.bootcamp.model.Stock;
import dio.santander.bootcamp.model.StockDTO;
import dio.santander.bootcamp.repository.StockRepository;
import dio.santander.bootcamp.util.MessageUtil;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    private StockMapper mapper = new StockMapper();

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessExceptions(MessageUtil.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntidy(dto);
        repository.save(stock);

        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO uptade(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()){
            throw new BusinessExceptions(MessageUtil.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntidy(dto);
        repository.save(stock);

        return mapper.toDto(stock);
       
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(id);

        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
    
}
