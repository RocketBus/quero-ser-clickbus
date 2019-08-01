$(document).ready(function () {

    if (window.location.href.substr(7) == "estoque-estudo.ef5.local/Produto/ProdutoHome") {
      somenteNumeros("resetar")
      document.getElementById('Quantidade').value = "";
      document.getElementById('quantidade2').value = "";
    }

    $("#controle-pesq2").val("");
    $("#controle-pesq").val("");
    $(".cadastrar-venda").val("");
});

function somenteNumeros(num) {
    var er = /[^0-9.]/;
    er.lastIndex = 0;
    var campo = num;
    if (er.test(campo.value)) {
        campo.value = "";
    }
}

$("#controler-btn").click('click', function () {
   
    var produto = $("select#ProdutoNome").val();
    var tamanho = $("select#ProdutoTamanho").val();
    var quantidade = $("#Quantidade").val();

    if (produto == "" || produto == null || produto == "default") {
        $("#errojq").empty();
        $("#errojq").append("Selecione o Produto");
        return false;
    } else if (quantidade == "" || quantidade == null) {
        $("#errojq").empty();
        $("#errojq").append("Informe a Quantidade");
        return false;
    } else {
        return true;
    }
        
});

$("#controler-btn3").click('click', function () {
    $("#errojq3").empty();
    var produto = $("select#ProdutoNome3").val();
    var varjoouatacado = $("select#ProdutoNome4").val();
    var tamanho = $("select#ProdutoTamanho3").val();
    var quantidade = $("#quantidade2").val();

    if (produto == "" || produto == null || produto == "default") {
        $("#errojq3").empty();
        $("#errojq3").append("Selecione o Produto");
        return false;
    } else if (quantidade == "" || quantidade == null) {
        $("#errojq3").empty();
        $("#errojq3").append("Informe a Quantidade");
        return false;
    } else if (varjoouatacado == "" || varjoouatacado == null || varjoouatacado == "default") {
        $("#errojq3").empty();
        $("#errojq3").append("Selecione se é varejo ou atacado");
        return false;
    }else {
        return true;
    }

});

$("#pesquisar").click('click', function () {
    var pesquisar = $(".ajust").val();
    $("#errojq1").empty();
    if (pesquisar == "" || pesquisar == null) {
        $("#errojq1").append("Digite o Nome do Cliente(a)!");
        return false;
    } 
});

$("#pesquisar2").click('click', function () {
    var pesquisar = $(".ajust2").val();
    if (pesquisar == "" || pesquisar == null) {
        $("#errojq2").append("Digite a Data!");
        return false;
    }
});

function validData(){
    
    
}

$("#cadastrarVisita").click('click', function () {
    $("#validarResponsavel").empty();
    $("#dataValidao").empty();
    var validar = $("#responsavel").val();
   
    if(validar == "default"){
        $("#validarResponsavel").append("Informe o Responsável!")
        return false;
    }

    //PEGAR DATA DO CAMPO
    var str = $('#data').val().substr(0,10)
    var myDate= str.split("/")
    var newDate=myDate[1]+","+myDate[0]+","+myDate[2];
    var novaData = new Date(newDate).getTime()
    novaData

    //PEGAR DATA AGORA
    var str = moment().format('L')
    var myDate= str.split("/")
    var newDate=myDate[1]+","+myDate[0]+","+myDate[2];
    var novaDataCompar = new Date(newDate).getTime()
    
    var dt = new Date();
    var horario = $('#data').val().substr(11, 15)
    var timeNow = dt.getHours() + ":" + dt.getMinutes();

    if(novaDataCompar > novaData){
        $("#dataValidao").append("Verifique a Data!")
        return false;
    } else if (novaDataCompar == novaData) {
        if (horario < timeNow) {
            $("#dataValidao").append("Verifique o Horário!")
            return false;
        }
    }

    

});






