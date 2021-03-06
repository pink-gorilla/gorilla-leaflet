(defproject org.pinkgorilla/gorilla-leaflet "0.2.8-SNAPSHOT"
  :description "A renderer for Pink Gorilla Notebook based on Leaflet maps."
  :url "https://github.com/pink-gorilla/pinkgorilla-leaflet"
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "0.2.6"]
                 [selmer "0.8.9"]
                 [org.pinkgorilla/gorilla-renderable "3.0.0"]]
  :profiles
  {:dev
   {:dependencies [[clj-kondo "2019.11.23"]
                   ;; [org.clojure/java.jdbc "0.4.1"]
                   ;; [postgresql "9.3-1102.jdbc41"]
                   ]
    :plugins      [[lein-cljfmt "0.6.6"]
                   [lein-cloverage "1.1.2"]]
    :aliases      {"clj-kondo" ["run" "-m" "clj-kondo.main"]}
    :cloverage    {:codecov? true}
    ;; TODO : Make cljfmt really nice : https://devhub.io/repos/bbatsov-cljfmt
    :cljfmt       {:indents {as->                [[:inner 0]]
                             with-debug-bindings [[:inner 0]]
                             merge-meta          [[:inner 0]]
                             try-if-let          [[:block 1]]}}}}
  :aliases {"bump-version" ["change" "version" "leiningen.release/bump-version"]}

  :release-tasks [["vcs" "assert-committed"]
                  ["bump-version" "release"]
                  ["vcs" "commit" "Release %s"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["bump-version"]
                  ["vcs" "commit" "Begin %s"]
                  ["vcs" "push"]])
