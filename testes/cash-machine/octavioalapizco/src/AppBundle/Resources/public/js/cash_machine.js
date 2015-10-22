var CashMachine=function(){
	var me=this;
	this.estado='LISTO';
	this.init=function(params){
		this.entregar_url=params.entregar_url;
		$('#btnCancelar').click(function(){
			me.prepararEntrada();
		});
		
		this.prepararEntrada();
		
		
		
		$('form[name="cash_machine"]').submit(function(e){
			e.preventDefault();
			me.entregarDinero();
		});
		
		$('#btnAceptar').click(function(){
			if (me.estado=='LISTO'){
				$('form[name="cash_machine"]').submit();
			}else{
				me.prepararEntrada();
			}
		});
		
		
		$('#pnlTecladoNumerico input[type="button"]').click(function(){
			var val=$(this).val();
			if (val!=""){
				var cantidad= $('input[name="cantidad_solicitada"]').val();
				$('input[name="cantidad_solicitada"]').val(cantidad.concat(val) );
				
			}
		});
		
		$('#pnlPantallaContent').after('<article style="display:none;text-align: left;" id="pnlRespuesta"><h3>wer</h3><label></label><ul></ul></article>');
		
		
	}
	
	this.prepararEntrada=function(){
		$('form[name="cash_machine"]').trigger("reset");
		// $('#pantalla h3').html('Bienvenido');
		// $('#dineroEntregado').remove();
		// $('#desgloce').remove();
		
		// $('#billetesDisponibles').show();
		// $('form[name="cash_machine"]').show();
		$('#pnlRespuesta').hide();
		$('#pnlPantallaContent').show();
			
		$('#pnlTecladoNumerico input[type="button"]').prop('disabled',false);
		$('#btnAceptar').prop('disabled',false);
		$('#btnCancelar').prop('disabled',false);
		$('input[name="cantidad_solicitada"]').focus( );
		me.estado='LISTO'
	}
	this.mostrarError=function(){
		
	}
	this.entregarDinero=function(){
		var cantidad=$('input[name="cantidad_solicitada"]').val( );
		 $('#pnlBotonesContent').block({ css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '16px', 
            '-moz-border-radius': '16px', 
            opacity: .5, 
			
            color: '#fff' 
        },message:null, }); 
		$('#pnlPantalla').block({ css: { 
            border: 'none', 
			'border-radius':'16px',
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '16px', 
            '-moz-border-radius': '16px', 
            opacity: .5, 
			
            color: '#fff' 
        },message:'<h1>Procesando</h1>' }); 
		$.ajax({
		  method: "POST",
		  url: this.entregar_url,
		  data: { esAjax:true, cantidad_solicitada: cantidad }
		})
		.done(function( respuesta ) {
			$('#pnlPantalla, #pnlBotonesContent').unblock();
			// $('#billetesDisponibles').hide();
			// $('form[name="cash_machine"]').hide();
			$('#pnlTecladoNumerico input[type="button"]').prop('disabled',true);
			$('#pnlRespuesta ul').html('');
			
			$('#pnlRespuesta').show();
			$('#pnlPantallaContent').hide();
			
			if (respuesta.success){
				$('#pnlRespuesta h3').html('Dinero Entregado: $'+respuesta.cantidad);
				me.estado='ENTREGANDO';
				$('#btnCancelar').prop('disabled',true);
				$('#pnlRespuesta label').html('En las Siguientes Denominaciones');
				var numDenominaciones=respuesta.billetes.length;
				for(var i=0; i<numDenominaciones; i++){
					$('#pnlRespuesta ul').append('<li>$'+respuesta.billetes[i].denominacion+' X '+respuesta.billetes[i].cantidad+'</li>');
				}
			}else if (respuesta.msg_error){
				$('#pnlRespuesta h3').html('Error:');
				$('#pnlRespuesta label').html(respuesta.msg_error);
				me.estado='ERROR';
				// $('#btnAceptar').prop('disabled',true);
			}else{
				$('#pnlRespuesta h3').html('Respueta desconocida, intente de nuevo');
				me.estado='ERROR';
			}
		 });
	}
	this.validar=function(){
		
	}
}