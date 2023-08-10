package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.repository.ItemOrderSupplierRepository;
import com.PersonalProject.Jemo.repository.OrderSupplierRepository;
import com.PersonalProject.Jemo.services.OrderSupplierService;
import com.PersonalProject.Jemo.validator.OrderSupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderSupplierServiceImpl implements OrderSupplierService {

    private OrderSupplierRepository orderSupplierRepository;
    private ItemOrderSupplierRepository itemOrderSupplierRepository;
    public OrderSupplierServiceImpl(OrderSupplierRepository orderSupplierRepository,ItemOrderSupplierRepository itemOrderSupplierRepository) {
        super();
        this.orderSupplierRepository = orderSupplierRepository;
        this.itemOrderSupplierRepository = itemOrderSupplierRepository;
    }

    @Override
    public OrderSupplierDto save(OrderSupplierDto orderSupplierDto) {
        List<String> error = OrderSupplierValidator.validator(orderSupplierDto);
        if(!error.isEmpty()){
            log.error("order supplier invalid {}",orderSupplierDto);
            throw new EntityNotValidException("order supplier invalid", ErrorCodes.ORDER_SUPPLIER_NOT_VALID, error);
        }
        return OrderSupplierDto.fromEntity(
                orderSupplierRepository.save(
                        OrderSupplierDto.toEntity(orderSupplierDto)));
    }

    @Override
    public OrderSupplierDto findById(Long id) {
        if(id == null){
            log.error("Order supplier id is null");
            return null;
        }
        return orderSupplierRepository.findById(id)
                .map(OrderSupplierDto::fromEntity)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Order supplier not found", ErrorCodes.ORDER_SUPPLIER_NOT_FOUND));
    }

    @Override
    public List<OrderSupplierDto> findAll() {
        return orderSupplierRepository.findAll().stream()
                .map(OrderSupplierDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Order supplier id is null");
        } else {
            orderSupplierRepository.deleteById(id);
        }

    }
}
