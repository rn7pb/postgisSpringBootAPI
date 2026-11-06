# postgisSpringBootAPI
Projeto exemplo, com uso de postgis, para a disciplina de banco de dados II.

## Banco de dados
```sql
CREATE DATABASE bdgeo;
CREATE USER aluno with encrypted password '@luno';
GRANT ALL PRIVILEGES ON DATABASE bdgeo TO aluno;
GRANT ALL PRIVILEGES ON SCHEMA public TO aluno;
-- dentro do bdgeo, como superusuário:
CREATE EXTENSION IF NOT EXISTS postgis;
```

## Importar os dados
Municípios (IBGE, 2025):
```
shp2pgsql -s 4326 -I -D /tmp/BR_Municipios_2025/BR_Municipios_2025.shp BR_municipios_2025 | psql -d bdgeo
```
Camadas de 2014 usadas pelas consultas (ajuste a pasta `2014` para onde descompactou o zip):
```
for f in aeroportos_2014 portos_2014 ferrovias_2014 rodovia_2014 armazens_2014 hidrovias_poligono_2014; do
  shp2pgsql -s 4326 -I -D 2014/$f.shp $f | psql -d bdgeo
done
```
Se importar como `postgres`, libere as tabelas para o usuário da aplicação:
```sql
GRANT ALL ON ALL TABLES IN SCHEMA public TO aluno;
```

## Executar
Rodar a classe `MeuProjetoApplication` (ou `mvn spring-boot:run`) e abrir:
- Swagger (as consultas): http://localhost:8080/swagger-ui/index.html
- Mapa (Leaflet): http://localhost:8080/

## Consultas
Dados de 2014 (cruzados com os municípios de 2025):
1. `GET /aeroportosDoMunicipio/{codigo}` - within
2. `GET /distanciaMunicipioAeroporto/{municipio}/{aeroporto}` - distance
3. `GET /portosProximos/{codigo}` - distance (raio em metros)
4. `GET /portosPorUf/{sigla}` - contains
5. `GET /ferroviasDoMunicipio/{codigo}` - intersects
6. `GET /municipiosDaFerrovia/{ferrovia}` - intersects
7. `GET /rodoviasDoMunicipio/{codigo}` - intersects
8. `GET /municipiosDaRodovia/{br}` - intersects
9. `GET /capacidadeArmazensDoMunicipio/{codigo}` - within + soma (toneladas)
10. `GET /areaHidroviasDoMunicipio/{codigo}` - intersects + área (km²)

Somente municípios de 2025:
- `GET /municipiosVizinhos/{codigo}` - touches
- `GET /distanciaEntreMunicipios/{municipioA}/{municipioB}` - distance

## Mapa
A página `src/main/resources/static/index.html` desenha o resultado das consultas no mapa (OpenStreetMap + Leaflet; precisa de internet para carregar o mapa-base). Ela usa endpoints de apoio (`/tracado...`, `/mapa...`, `/contexto...` e `/sugestoes/...`), que ficam ocultos do Swagger e **não contam como consultas**. Para tirar o mapa, basta apagar o `index.html`, os controllers `TracadoController` e `SugestaoController`, e os arquivos `TracadoRepository`, `SugestaoRepository`, `TracadoVO`, `SugestaoVO`, `ArmazemVO`, `HidroviaAreaVO` e `Wkt`.
