function ImgLoading(place) {


    var img = document.createElement("img");
    document.querySelector(place).appendChild(img);
    var att = document.createAttribute("id");
    att.value = "loader";
    img.setAttributeNode(att);
    var attr = document.createAttribute("style");
    attr.value = "display:block";
    img.setAttributeNode(attr);
    var atth = document.createAttribute("height");
    atth.value = "100px";
    img.setAttributeNode(atth);
    var attsrc = document.createAttribute("src");
    attsrc.value = "image/nen.png";
    img.setAttributeNode(attsrc);

}




