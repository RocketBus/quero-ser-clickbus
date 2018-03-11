var keys = document.querySelectorAll('#atm span');
        
for(var i = 0; i < keys.length; i++) {
    keys[i].onclick = function(e){
        if(this.className.indexOf("ignore")>=0)return;
        document.getElementById("response").innerHTML = "";
        var input = document.querySelector('.screen');
        var inputVal = input.innerHTML;
        var btnVal = this.innerHTML;
        if(this.className.indexOf("clear")>=0)
            input.innerHTML = input.innerHTML.substring(0,input.innerHTML.length-1);
        else if(this.className.indexOf("check")>=0)
        {
            var requested = input.innerHTML;
            if(requested.length>0)
            {
                requested = "/"+requested;
            }
            if(parseInt(input.innerHTML)>=1000000)
            {
                var continu = window.confirm("Es mucho dinero, ¿Está seguro que desea retirar tal cantidad?, piense en su seguridad y en lo peligroso que es tener esa cantidad en efectivo. El sistema puede demorar un par de segundos");        
                if(continu !== true) return;
            }
            input.innerHTML = '';
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open( "GET", "/getMoney"+requested, false );
            xmlHttp.send( null );
            var response = JSON.parse(xmlHttp.response);
            console.log(response);
            if(response.Error !== true)
            {
                var res = "La máquina le entregará:<br>";
                var algunBillete = false;
                var billetes = ["10", "20", "50", "100"];
                for(var i = 0; i < billetes.length; i++)
                {
                    if(response[i]>0){
                        algunBillete = true;
                        res += response[i] + " billete(s) de $" + billetes[i] + "<br>";    
                    }    
                }
                if(!algunBillete)
                {
                    res += " otra oportunidad para ingresar una cantidad, pues no ha ingresado ninguna";
                }
                document.getElementById('response').innerHTML = res;
            }
            else{
                document.getElementById('response').innerHTML = "El servidor encontró una Excepción '" 
                    + response.Exception + "'<br>con el siguiente mensaje: " 
                    + response.Message;
            }
        }
        else {
            input.innerHTML += btnVal;
        }
        e.preventDefault();
    } 
}