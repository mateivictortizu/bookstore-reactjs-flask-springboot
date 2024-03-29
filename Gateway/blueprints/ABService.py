from urllib import parse
from flask import Blueprint, request
import requests

abService = Blueprint('asService', __name__)

URL = "http://127.0.0.1:8090/api/bookcollection/"


@abService.route('/books')
def get_all_books():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    param = {}
    genre = request.args.get("genre")
    if genre is not None:
        param.update({"genre": genre})
    year = request.args.get("year")
    if year is not None:
        param.update({"year": year})
    page = request.args.get("page")
    if page is not None:
        param.update({"page": page})
    items_per_page = request.args.get("items_per_page")
    if items_per_page is not None:
        param.update({"items_per_page": items_per_page})
    r = requests.get(parse.urljoin(URL, 'books'), params=param)
    return r.content, r.status_code


@abService.route('/books/<ISBN>')
def get_book_by_isbn(ISBN):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    verbose = request.args.get("verbose")
    r = requests.get(parse.urljoin(URL, "books/" + ISBN), params={'verbose': verbose})
    return r.content, r.status_code


@abService.route('/books/<ISBN>', methods=['DELETE'])
def delete_book_by_isbn(ISBN):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    if r.json()["role"] != "ADMIN":
        return {"message": "Forbidden"}, 403

    r = requests.delete(parse.urljoin(URL, "books/" + ISBN))
    return r.content, r.status_code


@abService.route('/books', methods=['POST'])
def add_books():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code
    if r.json()["role"] != "ADMIN":
        return {"message": "Forbidden"}, 403
    json = request.json
    r = requests.post(parse.urljoin(URL, "books"), json=json)
    return r.content, r.status_code


@abService.route('/authors')
def get_authors():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    param = {}
    name = request.args.get("name")
    param.update({"name": name})
    match = request.args.get("match")
    param.update({"match": match})
    r = requests.get(parse.urljoin(URL, "authors"), params=param)
    return r.content, r.status_code


@abService.route('/authors', methods=['POST'])
def add_author():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    if r.json()["role"] != "ADMIN":
        return {"message": "Forbidden"}, 403

    json = request.json
    r = requests.post(parse.urljoin(URL, "authors"), json=json)
    return r.content, r.status_code


@abService.route('/authors/<ID>')
def get_author_by_id(ID):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    r = requests.get(parse.urljoin(URL, "authors/" + ID))
    return r.content, r.status_code


@abService.route('/authors/<ID>', methods=['DELETE'])
def delete_author_by_id(ID):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    if r.json()["role"] != "ADMIN":
        return {"message": "Forbidden"}, 403

    r = requests.delete(parse.urljoin(URL, "authors/" + ID))
    return r.content, r.status_code


@abService.route('/', methods=['OPTIONS'])
def get_all_methods():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    r = requests.options(URL)
    return r.content, r.status_code


@abService.route('/books', methods=['OPTIONS'])
def get_methods_books():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    r = requests.options(parse.urljoin(URL, "books"))
    return r.content, r.status_code


@abService.route('/authors', methods=['OPTIONS'])
def get_methods_authors():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    r = requests.options(parse.urljoin(URL, "authors"))
    return r.content, r.status_code


@abService.route('/books/<ISBN>/authors', methods=['POST'])
def add_authors_to_books(ISBN):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    if r.json()["role"] != "ADMIN":
        return {"message": "Forbidden"}, 403

    json = request.json
    r = requests.post(parse.urljoin(URL, "books/" + ISBN + "/authors"), json=json)
    return r.content, r.status_code


@abService.route('/books/<ISBN>/authors', methods=['GET'])
def get_authors_from_books(ISBN):
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    r = requests.get(parse.urljoin(URL, "books/" + ISBN + "/authors"))
    return r.content, r.status_code


@abService.route('/books/stockChange', methods=['POST'])
def stock_endpoint():
    head = request.headers
    r = requests.get(parse.urljoin("http://127.0.0.1:5000/", "check-token"), headers=head)

    if r.status_code != 200:
        return r.content, r.status_code

    json = request.json
    r = requests.post(parse.urljoin(URL, "books/stockChange"), json=json)
    return r.content, r.status_code
