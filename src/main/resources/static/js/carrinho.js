$(function () {
    calculaCarrinho();
    $('#btAdicionar').click(function (e) {
        e.preventDefault();
        novaLinhaCarrinho();
        calculaCarrinho();
    })

    $('#buy').click(function (e) {
        e.preventDefault();
        novaLinhaCarrinhoBuy();
        calculaCarrinho();
    })

    listar();
})

function novoProduto() {
    let valor = "229.99";
    let qtd = $('#qtd').val();
    let tam = $('#tam').val();
    if (qtd && tam) {
        let linhaHtml = {
            html: linhaProduto(qtd, tam, valor)
        }
        alert('Item adicionado ao carrinho');
        return linhaHtml;
    } else {
        alert('Item não foi adicionado ao carrinho, verifique a quantidade e o tamanho');
        return 1;
    }
}

function linhaProduto(qtd, tam, valor) {
    let tr =
        `
       <tr>
            <td style="text-align: center;"><img src="img/bm2.jpg" class="thumbnail" alt="..."></td>
            <td id="cQtd" class="align-tb">${qtd}</td>
            <td id="cTam" class="align-tb">${tam}</td>
            <td id="cValor" class="align-tb">${valor}</td>
            <td class="align-tb"><a href="#" onclick="deletarItem(this, event)"><i class="fa fa-2x fa-times-rectangle" style="
            color: slategray;"></i></a></td>
       </tr>`

    return tr;
}

function novaLinhaCarrinho() {
    $('#tbProdutos tbody').append(novoProduto().html);
    criarProduto();
}

function criarProduto() {
    let items = [];
    $('#tbProdutos tbody tr').each(function () {
        let colunas = $(this).children();
        let produto = new Produto(
            $(colunas[1]).text(),
            $(colunas[2]).text(),
            $(colunas[3]).text()
        )
        
        console.log(items.push(produto));
    })

    setArrayStorage(items);
    // return items;
}

function setArrayStorage(arr) {
    localStorage.setItem("listaProduto", JSON.stringify(arr));
}

function getArrayStorage() {
    return JSON.parse(localStorage.getItem("listaProduto"))|| [];
}

function listar() {
    let items = getArrayStorage();
    // let i = items.length;
    items.forEach(function (item, index) {
        $("#tbProdutos tbody").append(linhaProduto(item.qtd, item.tam, item.valor))
    });
}

function deletarItem(elemento, e) {
    e.preventDefault();
    let tr = $(elemento).parents("tr");
    $(tr).css('transition', 'all linear');
    $(tr).css('opacity', '.5');
    setTimeout(function () {
        $(tr).remove();
        criarProduto();
        calculaCarrinho();
    }, 1000)
}

function calculaCarrinho() {
    let items = getArrayStorage();
    let inputvalue = 0;
    let frete = 12.00;
    let desconto = 1.50;
    let total = 0;
    items.forEach(function (item, index) {
        var qtd = item.qtd;
        var valor = item.valor;
        inputvalue = inputvalue + (valor * qtd);
        total += inputvalue + frete - desconto;
    });

    $('#id1').val(inputvalue);
    $('#fret').val(frete);
    $('#desc').val(desconto);
    $('#tot').val(total);
};

function util() {
    if ($('#tbProdutos tbody tr').length === 0) {
        console.log("Não ha itens no carrinho");
        $('#container-total').hide();
        $('#container-vazio').show();
    } else {
        $('#container-total').show();
        $('#container-vazio').hide();
    }
}

////////////////////////////////////////
function novaLinhaCarrinhoBuy() {
    $('#tbProdutos tbody').append(novoProdutoBuy().html);
    criarProduto();
}

function novoProdutoBuy() {
    let valor = "229.99";
    let qtd = 1;
    let tam = 40;
    if (qtd && tam) {
        let linhaHtml = {
            html: linhaProduto(qtd, tam, valor)
        }
        alert('Item adicionado ao carrinho');
        return linhaHtml;
    } else {
        alert('Item não foi adicionado ao carrinho, verifique a quantidade e o tamanho');
        return 1;
    }
    
}
