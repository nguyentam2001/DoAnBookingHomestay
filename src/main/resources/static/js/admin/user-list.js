$(document).ready( ()=> {
    getUsers();
})
const URL = App.URL;
function getUsers(){
    const queryString = window.location.search;
    $.ajax({
        url: `${URL}`,
        type: App.GET,
        dataType: "json",
        async: false,
        success: function(res){
            console.log(res);
            render(res)
            setDisabledPrevNext()
            setActivePage()
        },
        error: function(e){
            toastr.error(e.responseJSON.message);
        }
    })


}
function render(arr){
    let html = "";
    arr.forEach(el => {
        html += `
        <tr>
            <th scope="row"><p class="link-color" onclick="viewCert('${el.id}');" ></i>${el.id}</p></th>
            <td >${el.certName}</td>
            <td> ${el.cost}</td>
            <td>${el.category==null?"No":el.category.name}</td>
            <td>
                <p class="link-color" data-toggle="modal" data-target="#exampleModal" ></i>Delete</p>
                 <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                         Do you want delete the  ${el.certName} ?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-primary" onclick="delete('${el.id}');" >Ok</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </div>
                                </div>
                         </div>
            </td>
        </tr>
        `
    })
    $("#user-list").html(html);
}

function view(id){
    let URL = App.URL_CERT + id;
    $.ajax({
        url: `${URL}`,
        type: App.GET,
        dataType: "json",
        success: function (res) {
                    $("[name='id']").val(res.id),
                    $("[name='certName']").val(res.certName),
                    $("[name='cost']").val(res.cost),
                    $("[name='category']").val(res.category==null?"no":res.category.name),
                    //set status =1 of update
                    status=App.PUT;
                    //set input is read only
                    setInputReadOnly(status);
        },
        error: function (e) {
            toastr.error(e.responseJSON.message);
        }
    })
}

function setInputReadOnly(status){
      if(status==App.PUT){
          $("[name='id']").attr("disabled",true);
          $("#saveBtn").text("Update");
      }else{
            $("[name='id']").attr("disabled",false);
            $("#saveBtn").text("Save");
            status=App.post;
      }
}

function delete(id){
    $.ajax({
        url: `${URL}/${id}`,
        type: App.DELETE,
        async: false,
        success: function(){
            toastr.success(App.DELETE_SUCCESS_MSG);
            $('.modal-backdrop').hide();
            //change page to prev page when numberOfElements of page ==1
             if(numberOfElements==1){
                   changePage(currentPage-1)
             }else{
                    getCerts()
             }
        },
        error: function(e){
            toastr.error(e.responseJSON.message);
        }
    })
}


