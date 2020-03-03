# HU1 - Buscar cotação do dolar comercial por data

Como sistema deve ser implementado um client para consumir um serviço de cotações (https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios) buscando por data a cotação e armazenar o retorno em um banco de dados.

A **Cotação** é composta das seguintes informações:

- id | ID da requisição
- dataRequisicao | Data da requisição
- dataCotacao | Data da cotação
- valorCompra | Cotação de compra
- valorVenda | Cotação de venda

URL do serviço: http://{host}/v1/cotacao/{data}

## Critérios de aceitação

| Nº | Descrição |
| -- | -- |
|CA1|Ao acessar a url do serviço informando uma data válida em formato (DD-MM-YYYY) o sistema deve apresentar os dados da **Cotação**.|
|CA2|Os dados da **Cotação** devem ser apresentados em formato JSON contendo todos as suas informações.|
|CA3|Caso não seja informado nenhuma data o sistema deve apresentar uma mensagem da obrigatoriedade deste parametro [MSGA001].|
|CA4|O serviço deve estar documentado no Swagger.|
|CA5|Monitorado com Prometheus e Grafana exibindo as métricas da API, DB e Infra.|

## Cenários de teste

| Nº | Regra | Descrição |
| -- |-- | -- |
|CT1|Data|O parâmetro de pesquisa data é obrigatório.|
|CT2|Data|O formato (DD-MM-YYYY) do parametro de pesquisa data deve ser validado.|
|CT3|Cotação|Todas as informações da **Cotação** devem estar presentes no retorno.|
|CT4|Cotação|Não deve ser consultado novas cotações em um intervalo de 2min, o sistema deve apenas apresentar as existentes.|
