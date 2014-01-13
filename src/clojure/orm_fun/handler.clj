(ns orm-fun.handler
  (:require [ring.util.response :refer [response]]
            [compojure.core :refer [routes GET]]
            [compojure.route :refer [resources]]
            [compojure.handler :refer [api]]
            [hiccup.page :refer [html5 include-css include-js]]
            [frodo :refer [repl-connect-js]]))

(defn page-frame []
  (html5
   [:head
    [:title "orm-fun - CLJS Single Page Web Application"]
    (include-js "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js")
    (include-js "//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js")
    (include-css "//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css")

    (include-js "/js/orm-fun.js")]
   [:body
    [:div#content]
    [:script (repl-connect-js)]]))

(defn app-routes []
  (routes
    (GET "/" [] (response (page-frame)))
    (resources "/js" {:root "js"})))

(defn app []
  (-> (app-routes)
      api))
