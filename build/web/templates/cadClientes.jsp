<br>
<div class="row" ng-hide="!page.editar">
    <div class="col-lg-8">
        <form class="form-horizontal well">
        <fieldset><legend>Clientes</legend><div  class="control-group">
<label class="control-label" for="page.recEdit.id">Codigo:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.id" id="page.recEdit.id" class="form-control"ng-disabled="true"  >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.nome">Nome:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.nome" id="page.recEdit.nome" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.endereco">Endere�o:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.endereco" id="page.recEdit.endereco" class="form-control" >
</div>
</div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.cidade_id">Cidade:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.cidade_id" 
                                        ng-options="item.nome for item in page.cidades" 
                                        >
                                    </select>
                                </div>
                            </div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.preco">Pre�o:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.preco" id="page.recEdit.preco" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.taxas">Taxas:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.taxas" id="page.recEdit.taxas" class="form-control" >
</div>
</div>

         <hr>
          <div class="form-actions">
<button type="submit" ng-click="page.actionSave()" class="btn btn-primary">Gravar</button>

<button type="submit" ng-click="page.actionVoltar()" class="btn btn-primary">Voltar</button>

          </div>
        </fieldset>
</form>
    </div>
</div>

<div class="row" ng-hide="page.editar">
    <div class="col-lg-12">
        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
        <table class="table table-bordered table-striped table-hover">
<thead>
<tr>
<th>C�digo</th>
<th>Nome</th>
<th>Endere�o</th>
<th>Cidade</th>
<th>Pre�os</th>
<th>Taxas</th>
</tr>
</thead>
<tbody>
<tr ng-repeat="item in page.registro" ng-click="page.actionEditar(item.id)">
<td>{{item.id}}</td>
<td>{{item.nome}}</td>
<td>{{item.endereco}}</td>
<td>{{item.cidade_id.nome}}</td>
<td class="text-right">{{item.preco}}</td>
<td class="text-right">{{item.taxas}}</td>
</tr>
</tbody>
</table>

        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
    </div>
</div>
