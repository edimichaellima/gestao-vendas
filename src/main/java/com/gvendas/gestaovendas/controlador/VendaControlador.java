package com.gvendas.gestaovendas.controlador;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.gvendas.gestaovendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaControlador {

    @Autowired
    private VendaServico vendaServico;

    @ApiOperation(value = "Listar vendas por cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(Long codigoCliente){
        return ResponseEntity.ok(vendaServico.listaVendaPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar vendas por código", nickname = "listarVendaPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(Long codigoVenda){
        return ResponseEntity.ok(vendaServico.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar venda", nickname = "salvarVenda")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long codigoCliente, @Valid @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(codigoCliente, vendaDto));
    }

    @ApiOperation(value = "Atualizar venda", nickname = "atualizarVenda")
    @PutMapping("/{codigoVenda}/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> atualizar(@PathVariable Long codigoVenda, @PathVariable Long codigoCliente, @Valid @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.ok(vendaServico.atualizar(codigoVenda, codigoCliente, vendaDto));
    }

    @ApiOperation(value = "Deletar venda", nickname = "deletarVenda")
    @PostMapping("/{codigoVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigoVenda) {
        vendaServico.deletar(codigoVenda);
    }
}
