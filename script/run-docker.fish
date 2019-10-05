#!/usr/bin/env fish

function run_docker
  argparse -n run_docker 'h/help' 'i/image=' -- $argv
  or return 1

  if set -lq _flag_help
    echo "run-docker.fish -i/--image <DOCKER_IMAGE>"
    return
  end

  set -lq _flag_image
  or set -l _flag_image shinyay/spring-cloud-security-gateway-gs

  docker run --rm -it -p 8080:8080 $_flag_image
end

run_docker $argv
