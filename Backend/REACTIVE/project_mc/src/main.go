package main

import (
	"log"

	"net/http"

	"github.com/gorilla/mux"

	"gorm.io/driver/postgres"

	"gorm.io/gorm"
)

func initRouter() {
	r := mux.NewRouter()

	r.HandleFunc("/test", test).Methods("GET")

	http.Handle("/", r)

	log.Fatal(http.ListenAndServe(":80", r))
}

var db *gorm.DB
var err error

type Body struct {
	message string
}

func main() {
	initRouter()
	dbConfig := "host=localhost user=postgres password=root dbname=progem port=5432"
	db, err = gorm.Open(postgres.Open(dbConfig))

	if err != nil {

		panic("failed to connect database")

	}

}
