let compraProdutos = [];
let total=0.00;
let desc=0.00;
let totalgeral=0.00;

$(document).ready(function() {
    lercompraProdutos();
});

function lercompraProdutos(){
    for (var i = 1; i <= 50; i++){
        if (localStorage.getItem("produto"+i)) {
            compraProdutos = JSON.parse(localStorage.getItem("produto"+i));

            console.log(compraProdutos);
            console.log('produtoid '+i+' : ', compraProdutos.id.produto.id);
            console.log('produtovl '+i+' : ', compraProdutos.valor);
            console.log('produtoqtd '+i+' : ', compraProdutos.quantidade);

            let compraProduto = new Object();
            compraProduto.id = new Object();
            compraProduto.id.produto = new Object();

            compraProduto.id.produto.id = compraProdutos.id.produto.id;
            compraProduto.imagem = compraProdutos.imagem;
            compraProduto.id.produto.nome = compraProdutos.id.produto.nome;
            compraProduto.valor = compraProdutos.valor;
            compraProduto.quantidade = compraProdutos.quantidade;

            adicionarLinhaCarrinho( criarLinha(compraProduto) );
            JSON.parse(localStorage.getItem(localStorage.key(i)));

            total += parseFloat(compraProdutos.valor * compraProdutos.quantidade);
            totalgeral = parseFloat(desc + total);
            document.getElementById("total").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(total);
            document.getElementById("desconto").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(desc);
            document.getElementById("totalgeral").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(totalgeral);

        }

    }

}

function adicionarProduto() {
	let compraProduto = new Object();
	compraProduto.id = new Object();
	compraProduto.id.produto = new Object();

	compraProduto.id.produto.id = $('#idProduto').val();
	compraProduto.imagem = $('#imagemProduto').val();
	compraProduto.id.produto.nome = $('#nomeProduto').val();
	compraProduto.valor = $('#valorProduto').val();
	compraProduto.quantidade = $('#quantidade').val();

//	compraProdutos.push(compraProduto);

    localStorage.setItem("produto"+compraProduto.id.produto.id, JSON.stringify(compraProduto));

    adicionarLinhaCarrinho( criarLinha(compraProduto) );
    alert("Produto adicionado ao carrinho!");
    location.reload(true);
}

function removerProduto(id, event) {
    compraProdutos =[];
    for (var i = 1; i <= 50; i++){
        if (localStorage.getItem("produto"+i)) {
            compraProdutos = JSON.parse(localStorage.getItem("produto"+i));
            if(compraProdutos.id.produto.id == id){
                localStorage.removeItem('produto'+i);
            }
        }
    }
    alert("id do produto removido: "+id);

    location.reload(true);

}

function criarLinha(compraProduto) {
	return `
    <tr id="${compraProduto.id.produto.id}">
        <td name="imagem"><a href="produto/${compraProduto.id.produto.id}"><img style="width: 80px; height: 80px;" src=${compraProduto.imagem}></img></a></td>
    	<td name="produto"><a href="produto/${compraProduto.id.produto.id}"><span>${compraProduto.id.produto.nome}</span></a></td>
		<td name="quantidade" style="text-align: center"><a href="produto/${compraProduto.id.produto.id}"><span>${compraProduto.quantidade}</span></a></td>
    	<td name="valor" style="text-align: right">
    		<span>${new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'})
					.format(compraProduto.valor)}</span>
    	</td>
    	<td name="valor" style="text-align: right">
    		<span>${new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'})
					.format(compraProduto.valor * compraProduto.quantidade)}</span>
    	</td>
    	<td><a onclick="removerProduto(${compraProduto.id.produto.id}, event);"><i class="fa fa-trash ml-2" title="Remover produto" data-toggle="tooltip"></i></a></td>
    </tr>`;
}

function criarLinhaReview(compraProduto) {
	return `
	<div class="row">
        <div class="col-md-6">
            <figure class="itemside  mb-4">
                <div class="aside"><img src="../images/items/1.jpg" class="border img-sm"></div>
                <figcaption class="info">
                    <p>Apple iPad (2019) 32Gb Wi-Fi gold </p>
                    <span class="text-muted">2x = $560 </span>
                </figcaption>
            </figure>
        </div> <!-- col.// -->
	`;
}


function adicionarLinhaCarrinho(linha) {
	if ($('#tbCompraProdutos tbody') == 0)
		$('#tbCompraProdutos').append('<tbody> </tbody>');

	$('#tbCompraProdutos tbody').append(linha);
}

function adicionarLinhaReview(linha) {
	if ($('#tbCompraProdutos2 tbody') == 0)
		$('#tbCompraProdutos2').append('<tbody> </tbody>');

	$('#tbCompraProdutos2').append(linha);
}

function finalizarCompra(urlDestino) {
	let pedido = new Object();
	pedido.valorfrete = 5.00;
	pedido.tipofrete = $('#quantidade').val();
	pedido.tipopagamento = 1;
	pedido.compraProdutos = compraProdutos;

//	$.ajax({
//		type: $('#frm').attr('method'),
//		url: $('#frm').attr('action'),
//		contentType : 'application/json',
//		data : JSON.stringify(pedido),
//		success: function() {
//			Swal.fire({
//				title: 'Salvo!',
//				text: 'Registro salvo com sucesso!',
//				type: 'success'
//				}).then((result) => {
//					window.location = urlDestino;
//				}
//			);//FIM swal()
//		},
//		error: function(data) {
//			console.log(data);
//			Swal.fire('Errou!', 'Falha ao salvar registro!', 'error');
//		}
//	}); //FIM ajax()
	return false;
}



