-- Respostas para o case de Data Delivery da Clickbus
-- Candidato: Yago Cunha de Paula

-- Desafios:

-- O CRM est� estudando os diferentes dom�nios de email para fazer otimiza��es de template baseado nos principais. 
-- Qual os 3 dom�nios que mais trouxeram dinheiro nas vendas confirmadas e qual o valor vendido de cada um desses dom�nios?

select
	substr(emailCliente, instr(emailCliente, '@') + 1) as 'Dom�nio'
,	sum(totalVenda) as 'Valor Vendido (R$)'
from
	vendas v
left join
	clientes c
on v.idCliente = c.idCliente
WHERE 
	statusVenda = 'confirmada'
Group by 1
ORDER by sum(totalVenda) DESC
limit 3;

-- Nosso time de tecnologia est� curioso quanto a adapta��o de cada regi�o do pa�s ao novo site mobile. 
-- Qual o share de receita das vendas confirmadas de cada plataforma (desktop e mobile) 
-- olhando para os clientes localizados nas regi�es Sudeste, Norte e Nordeste do pa�s ?

with share as (
select 
	case
		when estadoCliente = 'Sao Paulo' then 'Sudeste'
		when estadoCliente = 'Minas Gerais' then 'Sudeste'
		when estadoCliente = 'Espirito Santo' then 'Sudeste'
		when estadoCliente = 'Rio de Janeiro' then 'Sudeste'
		when estadoCliente = 'Amazonas' then 'Norte'
		when estadoCliente = 'Roraima' then 'Norte'
		when estadoCliente = 'Amapa' then 'Norte'
		when estadoCliente = 'Para' then 'Norte'
		when estadoCliente = 'Tocantins' then 'Norte'
		when estadoCliente = 'Rondonia' then 'Norte'
		when estadoCliente = 'Acre' then 'Norte'
		when estadoCliente = 'Maranhao' then 'Nordeste'
		when estadoCliente = 'Piaui' then 'Nordeste'
		when estadoCliente = 'Ceara' then 'Nordeste'
		when estadoCliente = 'Rio Grande do Norte' then 'Nordeste'
		when estadoCliente = 'Paraiba' then 'Nordeste'
		when estadoCliente = 'Pernambuco' then 'Nordeste'
		when estadoCliente = 'Alagoas' then 'Nordeste'
		when estadoCliente = 'Sergipe' then 'Nordeste'
		when estadoCliente = 'Bahia' then 'Nordeste'
		else 'Outros'
	end as Regiao
,	plataforma Plataforma
,	sum(totalVenda) Receita
from
	vendas v
left join
	clientes c
on v.idCliente = c.idCliente
where statusVenda = 'confirmada'
and regiao != 'Outros'
group by 1,2
order by Receita desc
)
select 
	Regiao
,	Plataforma 	
,	round(Receita / sum(Receita) over (partition by Regiao),2) Share
from share
group by 1,2
order by Receita desc;

-- Os assentos �mpares s�o de janela e os pares de corredor. 
-- Pensando em uma campanha nas TOP5 rotas em n�mero de vendas confirmadas e segmentada para clientes de Minas Gerais, 
-- voc� daria mais desconto em poltronas de janela ou corredor ? Por que ?

select 
	t.cidadeOrigem || ' - ' || t.cidadeDestino as rota
,	case
		when t.assento % 2 = 0 then 'Corredor'
		else 'Janela'
	end as tipoAssento
,   count(v.idVenda) qtdVenda
from
	vendas v
left join clientes c
	on v.idCliente = c.idCliente
left join tickets t
	on v.idVenda = t.idVenda
where estadoCliente = 'Minas Gerais'
and statusVenda = 'confirmada'
group by 1,2
order by qtdVenda desc
limit 5;

-- Resposta:

-- Nesse cen�rio, eu daria descontos em poltronas do corredor pois de acordo com a consulta acima elas s�o as menos vendidas.
-- Dando descontos nesse tipo de assento poderemos aumentar o n�mero de vendas de poltronas no corredor e consequentemente o total de passagens por viagem.

