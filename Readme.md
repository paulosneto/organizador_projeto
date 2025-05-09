
**Como** usuário autenticado,  
**Quero** criar, visualizar, editar e excluir projetos e tarefas no sistema,  
**Para** organizar e gerenciar as informações de cada projeto de tarefa de forma eficiente.


#### **Regras de Negócio para controlar projetos*
1. **Autenticação obrigatória**: Somente usuários autenticados podem acessar a tela de gerenciamento de projetos.
2. **Atributos do Projeto**:
    - **Nome do projeto**: Obrigatório e único.
    - **Descrição do projeto**: Opcional.
    - **Status do projeto**: Pode ser **Ativo** ou **Inativo**.
    - **Orçamento disponível**: Opcional.
3. A criação e edição de projetos devem respeitar as permissões do usuário logado.
4. A exclusão de um projeto só deve ser permitida se não houver registros dependentes associados.





#### **Regras de Negócio para controlar tarefas:**
1. **Autenticação obrigatória**: Somente usuários autenticados podem acessar a tela de gerenciamento de tarefas.
2. **Atributos da Tarefa**:
    - **Descrição da tarefa**: Obrigatório.
    - **Projeto**: Obrigatório (toda tarefa deve estar associada a um projeto existente).
    - **Data de Início**: Opcional.
    - **Data de Fim**: Opcional.
    - **Tarefa Predecessora**: Opcional (uma tarefa pode estar vinculada a outra anterior, indicando dependência).
    - **Status**: Pode ser **Concluída** ou **Não Concluída**.
3. O sistema deve garantir que a **Data de Fim** não seja anterior à **Data de Início**.
4. A exclusão de uma tarefa só deve ser permitida se ela **não for predecessora de outra tarefa**.
5. Deve ser possível listar todas as tarefas de um projeto e filtrá-las por **status (Concluída | Não Concluída)**.
6. O sistema deve permitir a **atualização do status da tarefa**, garantindo que apenas usuários autorizados possam marcá-la como "Concluída".
7. O sistema pode permitir a visualização do **progresso do projeto** com base nas tarefas concluídas e não concluídas.
