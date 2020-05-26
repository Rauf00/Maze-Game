package ca.mazegame.controllers;

import ca.mazegame.model.Game;
import ca.mazegame.wrappers.ApiBoardWrapper;
import ca.mazegame.wrappers.ApiGameWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GameController gives a response to HTTPS requests
 */
@RestController
public class GameController {
    List<ApiGameWrapper> apiGameWrappers = new ArrayList<>();
    List<Game> games = new ArrayList<>();
    private AtomicInteger nextId = new AtomicInteger();

    @GetMapping("/api/about")
    public String getName() {
        return "Rauf Shimarov";
    }

    @GetMapping("/api/games")
    public List<ApiGameWrapper> getApiGameWrappers() {
        return apiGameWrappers;
    }

    @PostMapping("/api/games")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiGameWrapper createNewGame() {
        Game game = new Game();
        ApiGameWrapper newGame = ApiGameWrapper.makeFromGame(game, nextId.incrementAndGet());
        games.add(game);
        apiGameWrappers.add(newGame);
        return newGame;
    }

    @GetMapping("/api/games/{id}")
    public ApiGameWrapper getApiGameWrapper(@PathVariable("id") int id) throws FileNotFoundException {
        for (ApiGameWrapper apiGameWrapper : apiGameWrappers) {
            if (apiGameWrapper.gameNumber == id) {
                ApiGameWrapper newApiGameWrapper = ApiGameWrapper.makeFromGame(games.get(id - 1), id);
                return newApiGameWrapper;
            }
        }
        throw new FileNotFoundException();
    }

    @GetMapping("/api/games/{id}/board")
    public ApiBoardWrapper getApiBoardWrapper(@PathVariable("id") int id) throws FileNotFoundException {
        for (ApiGameWrapper apiGameWrapper : apiGameWrappers) {
            if (apiGameWrapper.gameNumber == id) {
                ApiBoardWrapper apiBoardWrapper = ApiBoardWrapper.makeFromGame(games.get(id - 1));
                return apiBoardWrapper;
            }
        }
        throw new FileNotFoundException();
    }

    @PostMapping("/api/games/{id}/moves")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void makeMove(@RequestBody String move,
                         @PathVariable("id") int id) throws FileNotFoundException {
        for (ApiGameWrapper apiGameWrapper : apiGameWrappers) {
            if (apiGameWrapper.gameNumber == id) {
                Game selectedGame = games.get(id - 1);
                if (move.equals("MOVE_CATS")) {
                    selectedGame.moveCats();
                    return;
                } else if(move.equals("MOVE_UP") ||
                        move.equals("MOVE_DOWN") ||
                        move.equals("MOVE_LEFT") ||
                        move.equals("MOVE_RIGHT")){
                    //selectedGame.updateVisibleCells(selectedGame.getMaze(), selectedGame.getMouse().getCoordinate());
                    String bumpedToWall = selectedGame.moveMouse(move);
                    if(bumpedToWall.equals("BUMPED_TO_WALL")){
                        throw new IllegalArgumentException();
                    }
                    return;
                } else{
                    throw new IllegalArgumentException();
                }
            }
        }
        throw new FileNotFoundException();
    }

    @PostMapping("/api/games/{id}/cheatstate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cheat(@RequestBody String cheat,
                      @PathVariable("id") int id) throws FileNotFoundException {
        for (ApiGameWrapper apiGameWrapper : apiGameWrappers) {
            if (apiGameWrapper.gameNumber == id) {
                Game selectedGame = games.get(id - 1);
                if (cheat.equals("1_CHEESE")) {
                    selectedGame.setTotalNumCheese(1);
                    return;
                } else if (cheat.equals("SHOW_ALL")){
                    selectedGame.showAllMaze();
                    return;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }
        throw new FileNotFoundException();
    }

    // Create Exception Handle
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentException() {
        // Nothing to do
    }
}
