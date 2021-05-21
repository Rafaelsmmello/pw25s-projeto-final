let compraProdutos = [];
let total=0.00;
let desc=0.00;
let totalgeral=0.00;
let pagamento;
let qtditens=10;
$(document).ready(function() {
    lercompraProdutos();
    document.getElementById("valortotal").value = totalgeral;
    var idusuariop = document.getElementById("idusuariop").textContent;
    document.getElementById("idUsuario").value = idusuariop;

//    const rbs = document.querySelectorAll('input[name="choice"]');
//        let selectedValue;
});

function lercompraProdutos(){
    for (var i = 1; i <= qtditens; i++){
        if (localStorage.getItem("produto"+i)) {
            compraProdutos = JSON.parse(localStorage.getItem("produto"+i));

//            console.log(compraProdutos);
//            console.log('produtoid '+i+' : ', compraProdutos.id.produto.id);
//            console.log('produtovl '+i+' : ', compraProdutos.valor);
//            console.log('produtoqtd '+i+' : ', compraProdutos.quantidade);

            let compraProduto = new Object();
            compraProduto.id = new Object();
            compraProduto.id.produto = new Object();

            compraProduto.id.produto.id = compraProdutos.id.produto.id;
            compraProduto.imagem = compraProdutos.imagem;
            compraProduto.id.produto.nome = compraProdutos.id.produto.nome;
            compraProduto.valor = compraProdutos.valor;
            compraProduto.quantidade = compraProdutos.quantidade;

            adicionarLinhaReview( criarLinhaReview(compraProduto) );
            JSON.parse(localStorage.getItem(localStorage.key(i)));

            total += parseFloat(compraProdutos.valor * compraProdutos.quantidade);
            totalgeral = parseFloat(desc + total);
            document.getElementById("total").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(total);
            document.getElementById("desconto").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(desc);
            document.getElementById("totalgeral").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(totalgeral);
            document.getElementById("totalgeral2").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(totalgeral);
            document.getElementById("desconto2").innerHTML = new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'}).format(desc);
//            document.getElementById("pagamento").innerHTML = pagamento;
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
}

function removerProduto(id, event) {
    compraProdutos =[];
    for (var i = 1; i <= qtditens; i++){
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


function criarLinhaReview(compraProduto) {
	return `
        <div class="col-md-6">
            <figure class="itemside  mb-4">
                <div class="aside"><img src=${compraProduto.imagem} class="border img-sm"></div>
                <figcaption class="info">
                    <p>${compraProduto.id.produto.nome}</p>
                    <span class="text-muted">${compraProduto.quantidade} und. = ${new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'})
                    .format(compraProduto.valor*compraProduto.quantidade)} </span>
                </figcaption>
            </figure>
        </div> <!-- col.// -->
	`;
}

function adicionarLinhaReview(linha) {
	if ($('#tbCompraProdutos2 tbody') == 0)
		$('#tbCompraProdutos2').append('<tbody> </tbody>');

	$('#tbCompraProdutos2').append(linha);
}

function finalizarCompra() {
localStorage.clear();

//alert("entrou no ajax");
//
////	pedido.valorfrete = 5.00;
////	pedido.tipofrete = 1;
//
////    let pedidoitempk = [];
////    let pedidoitem = [];
//var j=0;
//        for (var i = 1; i <= qtditens; i++){
//            if (localStorage.getItem("produto"+i)) {
//                compraProdutos = JSON.parse(localStorage.getItem("produto"+i));
//                j++;
//            }
//        }
//window.location = '/pedido/add/produtoitem';
////    let pedidoitem = new Object();
////    pedidoitem.valor = 1;
////    pedidoitem.pedido_id= 1;
//
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
//					window.location = '/pedido';
//				}
//			);//FIM swal()
//		},
//		error: function(data) {
//			console.log(data);
//			Swal.fire('Errou!', 'Falha ao salvar registro!', 'error');
//		}
//	}); //FIM ajax()
//	return false;
}
//



