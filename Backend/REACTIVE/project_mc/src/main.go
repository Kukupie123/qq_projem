package main

import (
	"fmt"

	"log"

	"net/http"

	"github.com/gorilla/mux"

	"time"

	"gorm.io/driver/postgres"

	"gorm.io/gorm"

	"encoding/json"

	_ "github.com/jinzhu/gorm/dialects/postgres"
)

var db *gorm.DB
var err error

func main() {
	initRouter()
	initDB()
	db.Table("users")

}
func initRouter() {
	r := mux.NewRouter()

	r.HandleFunc("/test", test).Methods("GET")

	http.Handle("/", r)

	log.Fatal(http.ListenAndServe(":80", r))
}

func initDB() {
	host := "localhost"
	port := "5432"
	user := "postgres"
	schema := "progem"
	password := "root"

	url := fmt.Sprintf("host=%s user=%s dbname=%s sslmode=disable password=%s port=%s", host, user, schema, password, port)
	db, err = gorm.Open(postgres.Open(url))
	if err != nil {
		print(err)
	} else {
		print("Successfully Connected")

	}

	db.AutoMigrate(&User{})

	db.AutoMigrate(&Project{})

	var (
		users = &User{
			ID:   "go@gmail.com",
			Cred: "123",
		}
	)

	fmt.Println("SUP")
	print("SUP FROM PRINT")
	db.Take(&users)


}

type Test struct {
	//The JSON key name should be message
	Message string `json:"msg"`
	//Capital vars are exported and small vars are not
}

func test(w http.ResponseWriter, _ *http.Request) {
	var user User
	db.Take(&user)
	print(user.ID)
	body := Test{Message: user.ID}

	err := json.NewEncoder(w).Encode(body)
	if err != nil {
		w.WriteHeader(500)
	}

}

type Project struct {
	ID          int
	Title       string
	Description string
	Ancestry    string
	Timestamp   *time.Time
	IsCompleted bool
	RulesId     int
	UserId      int
}

type User struct {
	ID   string
	Cred string
}
