print('=' * 50)
print('{:^50}'.format('Cash Machine'))
print('{:^50}'.format('As notas disponiveis são:'))
print('{:^50}'.format('R$100,00 R$50,00 R$20,00 R$10,00'))
print('=' * 50)
print('{:^50}'.format('Insira o valor do seu saque'))
valor = int(input('{:^50}'.format('R$'))) #input de informação
saque = valor #variavel para valor
notas = 0 #variavel para contar notas
restsaque = 0 #nova variavel de valor


while True:
        if saque >= 100:
            while saque >= 100:  # priorização de notas de 100
                notas = notas + 1 #soma notas = quantidade de notas
                restsaque = saque - 100 #recalcula o valor do saque
                saque = restsaque #forma um novo valor de saque
            print(f'Total de cedulas de R$100 será de {notas}') #saida
            notas = 0

        elif saque >= 50:
            notas = notas + 1
            restsaque = saque - 50
            saque = restsaque
            print(f'Total de cedulas de R$50 será de {notas}')
            notas = 0
        elif saque >= 20:
            while saque >= 20:
                notas = notas + 1
                restsaque = saque - 20
                saque = restsaque
            print(f'Total de cedulas de R$20 será de {notas}')
            notas = 0
        elif saque >= 10:
            notas = notas + 1
            restsaque = saque - 10
            saque = restsaque
            print(f'Total de cedulas de R$10 será de {notas}')
            notas = 0
        elif saque == 0:
            print(f'Insira um valor!')
            break
        elif saque < 0:
            print(f'Insira um valor valido!')
            break