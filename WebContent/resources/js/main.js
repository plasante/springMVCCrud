function deleteBook(bookId){
    if(confirm('Do you want to delete this Book ?')){
        var url = 'delete/'+bookId;
        window.location.href = url;
    }
}

function deleteEntity(entityId){
    if(confirm('Do you want to delete this Entry ?')){
        var url = 'roles/delete/'+entityId;
        window.location.href = url;
    }
}