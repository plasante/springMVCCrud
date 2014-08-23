function deleteBook(bookId){
    if(confirm('Do you want to delete this Book ?')){
        var url = 'delete/'+bookId;
        window.location.href = url;
    }
}