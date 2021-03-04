

valor = int(input("Qual valor você quer sacar? R$"))
total = valor
ced = 100
totalced = 0


while True:
    if total >=ced:
        total -= ced
        totalced += 1
    else:
        if totalced > 0:
            print(f'Total de {totalced} cédulas de R${ced}')
            if ced == 100:
             ced == 50
             if total == 0:
                 break
        elif ced == 50:
            ced == 20
            if total == 0:
                break
        elif ced == 20:
            ced == 10
            totalced == 0
            if total == 0:
                break

