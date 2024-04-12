package JuDBu.custommaster.controller;

import JuDBu.custommaster.dto.ord.OrdDto;
import JuDBu.custommaster.service.ord.OrdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdController {
    private final OrdService ordService;

    @GetMapping
    public List<OrdDto> readAll() {
        return ordService.readAll();
    }

    @GetMapping("{id}")
    public OrdDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return ordService.readOne(id);
    }

    @GetMapping("{id}/payment")
    public Object readTossPayment(
            @PathVariable("id")
            Long id
    ){
        return ordService.readTossPayment(id);
    }
}
