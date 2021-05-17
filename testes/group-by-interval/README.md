# Challenge-Group-By-Interval

## Básico do projeto

### Execução do projeto
  <ul>
  <li>mvn clean package -Dmaven.test.skip=true</li>
  <li>docker-compose build --no-cache</li>
  <li>docker-compose up</li>
  </ul>

### Exemplo de request
   <ul>
    <li>POST <a>http://localhost:8080/api/group-by-interval</a></li>
    <li>Content-Type: application/json</li>
    <li>
        body: {
            "range": 15,
            "numberSet": [10, 1, -20,  14, 99, 136, 19, 20, 117, 22, 93, 120, 131]
        }
    </li>
   </ul>


