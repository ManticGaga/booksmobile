package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("crud_user").document(crud.getDocumentId()).set(crud);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public CRUD getCRUD_Document(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("crud_user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CRUD crud;
        if(document.exists()){
            crud = document.toObject(CRUD.class);
            return crud;
        }
        return null;
    }

    public List<CRUD> getCRUD_Collection() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collection = dbFirestore.collection("crud_user");
        ApiFuture<QuerySnapshot> future = collection.get();
        QuerySnapshot snapshot = future.get();
        List<CRUD> result = new ArrayList<>();
        for (DocumentSnapshot document : snapshot.getDocuments()) {
            result.add(document.toObject(CRUD.class));
        }
        return result;
    }

    public String updateCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getDocumentId()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteCRUD(String documentId){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("crud_user").document(documentId).delete();
        return "Successfully deleted " + documentId;
    }
}