# OOP TERM PROJECT
Making a Simple Drawing Application with Java for a Term Project
<br/>


## <img src="https://em-content.zobj.net/source/microsoft-teams/363/thinking-face_1f914.png" alt="프로젝트 소개" style="width:1em; height:1em"/> 프로젝트 소개

객체지향프로그래밍 강의 텀 프로젝트의 진행 과정과 변경사항을 기록하기 위해 만든 Git 레포지토리입니다. 이 프로젝트는 `java.awt`와 `javax.swing` 등의 라이브러리를 활용하여 기초적인 자바 스킬을 향상시키기 위해 진행합니다. 초기 커밋은 Hong Min 교수님께서 제공해 주셨으며, 미션 1~4까지의 과제가 포함되어 있습니다. 이 과제들은 초반에 해결되었고, 더 나은 그림판 애플리케이션으로 발전시켜보며 자바에 대해 더 깊이 이해하고자 합니다.

<details>
<summary><img src="https://em-content.zobj.net/source/microsoft-teams/363/check-mark_2714-fe0f.png" alt="해결한 미션" style="width:1em; height:1em;"/><b>&nbsp;해결한 미션 설명</b></summary>

## <img src="https://em-content.zobj.net/source/microsoft-teams/363/check-mark_2714-fe0f.png" alt="해결한 미션" style="width:1em; height:1em"/> 해결한 미션

### 미션1
`[Mission1]`부분에 `ActionListener`를 넣어 코드가 정상적으로 작동하도록 하였습니다.
`ColorHandler`와 `MenuHandler` 클래스가 이벤트를 처리하기 위해서는 `ActionListener` 인터페이스를 구현해야 합니다. `ActionListener`는 `actionPerformed(ActionEvent event)` 메서드를 정의하여, 사용자 인터페이스 이벤트(예: 버튼 클릭)를 처리할 수 있게 합니다. 


### 미션2
`draw(Graphics g)` 메서드는 `Graphics` 객체를 사용하여 사각형을 그리는 역할을 합니다. 좌표 및 크기를 계산하여 사각형을 그릴 수 있도록 구현해야 합니다.

```java
@Override
public void draw(Graphics g) {
    g.setColor(getColor());
    int x = Math.min(getStartX(), getEndX());
    int y = Math.min(getStartY(), getEndY());
    int width = Math.abs(getStartX() - getEndX());
    int height = Math.abs(getStartY() - getEndY());

    if (getFill()) {
        g.fillRect(x, y, width, height);
    } else {
        g.drawRect(x, y, width, height);
    }
}
```

- `draw` 메서드는 `Graphics` 객체를 사용하여 사각형을 그립니다.
- `getColor()` 메서드를 호출하여 도형의 색상을 설정합니다.
- `Math.min` 메서드를 사용하여 시작 좌표와 끝 좌표 중 작은 값을 선택하여 `x`와 `y` 좌표를 설정합니다.
- `Math.abs` 메서드를 사용하여 시작 좌표와 끝 좌표의 차이로 너비와 높이를 계산합니다.
- `getFill()` 메서드가 `true`일 경우, `fillRect` 메서드를 사용하여 사각형을 채워서 그립니다.
- `getFill()` 메서드가 `false`일 경우, `drawRect` 메서드를 사용하여 사각형의 테두리만 그립니다.


### 미션3
`[Mission 3]` 부분에 아래와 같은 코드를 추가하여 `mousePressed` 메서드를 완성했습니다.

```java
case "Oval":
    currentShapeObject = new GcuOval(event.getX(), event.getY(), 
                                     event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
    break;
```

- `mousePressed` 메서드는 마우스 클릭 이벤트를 처리합니다.
- `Oval` 선택 시, `GcuOval` 객체를 생성하여 `currentShapeObject`에 할당합니다.
- `GcuOval` 생성자는 시작 좌표 (`event.getX()`, `event.getY()`), 현재 색상 (`currentShapeColor`), 및 채우기 여부 (`currentShapeFilled`)를 사용하여 초기화합니다.
  


### 미션4

```java
@Override
public void mouseReleased(MouseEvent event) {
    currentShapeObject.setEndX(event.getX());
    currentShapeObject.setEndY(event.getY());
    myShapes.addFront(currentShapeObject);
    currentShapeObject = null;
    clearedShapes.makeEmpty();
    repaint();
}

@Override
public void mouseDragged(MouseEvent event) {
    currentShapeObject.setEndX(event.getX());
    currentShapeObject.setEndY(event.getY());
    repaint();
}
```

- **mouseReleased**: 마우스 버튼을 놓았을 때 호출됩니다.
  - 현재 도형의 끝 좌표를 업데이트합니다.
  - 도형을 `myShapes` 리스트에 추가합니다.
  - `currentShapeObject`를 null로 설정하고, `clearedShapes`를 초기화한 후, 화면을 다시 그립니다.

- **mouseDragged**: 마우스를 드래그할 때 호출됩니다.
  - 현재 도형의 끝 좌표를 업데이트합니다.
  - 화면을 다시 그립니다.

이 두 메서드는 마우스 이벤트를 처리하여 도형을 그리기 위해 사용됩니다.
</details>

<br/>

## <img src="https://em-content.zobj.net/source/microsoft-teams/363/shooting-star_1f320.png" alt="가산점 포인트" style="width:1em; height:1em"/> 가산점 포인트


그림을 저장한 후에 다시 그림판으로 불러왔을 때, `LinkedList`로 저장된 Shapes들을 그대로 다시 불러와서 `Undo`와 `Redo`가 작동하게 하면, 교수님께서 가산점을 주신다고 하셨습니다. 이때, `LinkedList`를 어떤 형식과 확장자로 저장하는지는 자율이라고 하셨습니다.

### JSON 형태로 myShapes값을 저장
우선, myShapes들을 가장 직관적이고 간단하게 저장할 수 있는 방법은 JSON 형식을 활용하는 것이라고 생각했습니다. JSON 형식은 `key-value` 형식으로 저장되어, myShapes 안에 있는 `fill`, `rgba`, `startX`, `startY`, `endX`, `endY` 값을 명시하여 저장하기에 좋았습니다.

다만, Java에서 JSON 형식으로 parsing 하기 위해서는 매우 복잡한 절차를 따라야했기 때문에, `jackson` 라이브러리를 활용하여 JSON 타입으로 인코딩하고 디코딩했습니다.

다음으로 저는 myShapes 정보를 `.txt`, `.json` 등의 다른 확장자로 따로 저장하고 싶지 않았습니다. 그래서 해킹 등에서 자주 활용되는 `Steganography` 기술을 활용하여 하나의 `.png` 파일에 이미지와 myShapes 값을 모두 저장하기로 하였습니다.

### Steganography란
> 스테가노그래피(Steganography)는 정보 은닉의 한 방법으로, 메시지를 다른 비밀 메시지에 숨기는 기술입니다. 즉, 눈에 보이는 데이터 뒤에 실제 중요한 정보를 감추는 방식입니다. 주로 디지털 이미지, 오디오 파일, 비디오 파일 등 다양한 매체에 숨겨진 정보를 삽입하여 데이터를 보호하거나 비밀 메시지를 전달하는 데 사용됩니다. 이 기술은 정보의 존재를 숨기기 때문에 해킹이나 데이터 유출로부터 중요한 정보를 보호할 수 있습니다. 예를 들어, 이미지 파일에 숨겨진 텍스트 데이터는 이미지가 그대로 보여지기 때문에 겉으로는 아무런 변화가 없지만, 특정 소프트웨어를 사용하면 숨겨진 정보를 추출할 수 있습니다.

.png 파일은 끝을 "IEND" 청크로 표시하며, 숨기는 데이터는 IEND 청크 뒤에 추가되어 png 이미지 품질이 깨지지 않으면서 데이터를 숨길 수 있습니다.

### 숨기는 방법
1.	**JSON 데이터를 이진 문자열로 변환**: JSON 데이터를 각 문자마다 8비트의 이진 문자열로 변환합니다.
2.	**종료 마커 추가**: 데이터의 끝을 표시하기 위해 이진 데이터에 “00000011”과 같은 종료 마커를 추가합니다.
3.	**IEND 청크 뒤에 데이터 삽입**: 변환된 이진 데이터를 이미지 파일의 IEND 청크 뒤에 삽입합니다.

이렇게 하여 이미지의 시각적 품질을 유지하면서도 데이터를 안전하게 숨겨 저장할 수 있었습니다.

<br/>

## <img src="https://em-content.zobj.net/source/microsoft-teams/363/hammer-and-wrench_1f6e0-fe0f.png" alt="추가된 기능 및 개선사항" style="width:1em; height:1em"/> 추가된 기능 및 개선사항

### 완성본에서 마우스 좌표값 제거
그림판에서 그림을 다 그리고 파일을 저장했을때, 좌측 하단에 표시되는 마우스 좌표값이 같이 저장되는 문제를 개선했습니다.

### 옵션 단축키 추가
일상생활에서 자주 쓰이는 단축키들을 추가하여 바로 활용할 수 있도록 하였습니다.
 - **Undo**: `Command + Z` 
 - **Redo**: `Command + Shift + Z`
 - **Save**: `Command + S`

### 옵션 아이콘 추가
보다 직관적으로 어떤 액션이 실행되는지 유저가 파악할 수 있도록 파일 옵션에 아이콘을 추가하였습니다.

### 컬러 팔렛트로 수정
기존의 버튼 6색상 체제에서 더 많은 색을 사용자가 선택할 수 있도록 `JColorChooser`를 활용하여 색을 자유롭게 선택할 수 있습니다.

### 파일 저장 방식 수정
파일명만 입력하면 루트 디렉토리에 저장이 되던 형식에서,`JFileChooser`를 활용해 저장할 때 파일 위치도 함께 저장할 수 있도록 수정하였습니다.

### 파일 열람 방식 수정
파일명만 입력하면 루트 디렉토리에서 해당 파일이 열리던 형식에서,`JFileChooser`를 활용해 파일 위치를 선택하여 불러올 수 있도록 하였습니다.

<br/>

## 프로젝트에 포함된 오픈소스

- Jackson JSON Processor: FasterXML. (n.d.). Retrieved from [https://github.com/FasterXML/jackson](https://github.com/FasterXML/jackson)  
  License: [Apache License 2.0](https://github.com/FasterXML/jackson/blob/master/LICENSE)

- Microsoft 3D Animated Emoji. (n.d.). Retrieved from [https://github.com/microsoft/fluentui-emoji](https://github.com/microsoft/fluentui-emoji)  
  License: [MIT License](https://github.com/microsoft/fluentui-emoji/blob/main/LICENSE)

- FontAwesome (Pro Plan). (n.d.). Retrieved from [https://fontawesome.com](https://fontawesome.com)  
  License: [Font Awesome Pro License](https://fontawesome.com/license/pro