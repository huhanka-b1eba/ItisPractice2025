package aa.tulybaev.user.repository.impl.file;

import aa.tulybaev.user.entity.User;
import aa.tulybaev.user.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryFile implements UserRepository {

    private final String fileName;

    public UserRepositoryFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            writer.write(UserFileUtil.toList(user));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> findAll() {
        try {
            return Files.lines(Path.of(fileName)).
                    map(UserFileUtil::fromList).
                    toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        List<User> list = findAll();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUuid().equals(user.getUuid())) {
                    bw.write(UserFileUtil.toList(user));
                } else {
                    bw.write(UserFileUtil.toList(list.get(i)));
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(String uuid) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (uuid.equals(parts[0])) {
                    return Optional.of(UserFileUtil.fromList(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
